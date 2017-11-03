package com.pursue.corner.integrated.rw.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import com.pursue.corner.integrated.rw.DataSourceRwContext;
import com.pursue.corner.integrated.rw.RwDataSource;

public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

	// 如配置文件中未指定数据源类型，使用该默认值

	private static final Object DATASOURCE_TYPE_DEFAULT = "org.apache.tomcat.jdbc.pool.DataSource";
	private ConversionService conversionService = new DefaultConversionService();
	private PropertyValues dataSourcePropertyValues;
	//主写数据源
	private DataSource defaultWriteDataSource;
	//读数据源
	private DataSource readDataSource;
	//多个数据源
	private Map<String, DataSource> multiDataSources = new HashMap<String, DataSource>();

	@Override
	public void setEnvironment(Environment environment) {
		System.out.println("DynamicDataSourceRegister.setEnvironment()");
		initDefaultDataSource(environment);
		initReadDataSources(environment);
		initMultiDataSources(environment);
	}

	/**
	 * 加载默认写(主数据源)数据源配置
	 */
	private void initDefaultDataSource(Environment env) {
		//读取数据源配置
		RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.write.");
		Map<String, Object> dsMap = new HashMap<String, Object>();
		dsMap.put("type", propertyResolver.getProperty("type"));
		dsMap.put("driverClassName", propertyResolver.getProperty("driverClassName"));
		dsMap.put("url", propertyResolver.getProperty("url"));
		dsMap.put("username", propertyResolver.getProperty("username"));
		dsMap.put("password", propertyResolver.getProperty("password"));
		// 创建数据源;
		defaultWriteDataSource = buildDataSource(dsMap);
		dataBinder(defaultWriteDataSource, env);
	}
	/**
	 * 加载默认读数据源配置.
	 */
	private void initReadDataSources(Environment env) {
		// 读取数据源配置
		RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.read.");
		Map<String, Object> dsMap = new HashMap<String, Object>();
		dsMap.put("type", propertyResolver.getProperty("type"));
		dsMap.put("driverClassName", propertyResolver.getProperty("driverClassName"));
		dsMap.put("url", propertyResolver.getProperty("url"));
		dsMap.put("username", propertyResolver.getProperty("username"));
		dsMap.put("password", propertyResolver.getProperty("password"));
		// 创建数据源;
		readDataSource = buildDataSource(dsMap);
		dataBinder(readDataSource, env);
	}
	/**
	 * 加载多个数据源配置.
	 */
	private void initMultiDataSources(Environment env) {
		// 读取配置文件获取更多数据源，也可以通过defaultDataSource读取数据库获取更多数据源
		RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.multi.");
		String dsPrefixs = propertyResolver.getProperty("names");
		for (String dsPrefix : dsPrefixs.split(",")) {// 多个数据源
			Map<String, Object> dsMap = propertyResolver.getSubProperties(dsPrefix + ".");
			DataSource ds = buildDataSource(dsMap);
			multiDataSources.put(dsPrefix, ds);
			dataBinder(ds, env);
		}
	}
	@SuppressWarnings("unchecked")
	public DataSource buildDataSource(Map<String, Object> dsMap) {
		Object type = dsMap.get("type");
		if (type == null) {
			type = DATASOURCE_TYPE_DEFAULT;// 默认DataSource
		}
		Class<? extends DataSource> dataSourceType;
		try {
			dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);
			String driverClassName = dsMap.get("driverClassName").toString();
			String url = dsMap.get("url").toString();
			String username = dsMap.get("username").toString();
			String password = dsMap.get("password").toString();
			DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url)
					.username(username).password(password).type(dataSourceType);
			return factory.build();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 为DataSource绑定更多数据
	 */
	private void dataBinder(DataSource dataSource, Environment env) {
		RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
		dataBinder.setConversionService(conversionService);
		dataBinder.setIgnoreNestedProperties(false);// false
		dataBinder.setIgnoreInvalidFields(false);// false
		dataBinder.setIgnoreUnknownFields(true);// true
		if (dataSourcePropertyValues == null) {
			Map<String, Object> rpr = new RelaxedPropertyResolver(env, "spring.datasource").getSubProperties(".");
			Map<String, Object> values = new HashMap<>(rpr);
			// 排除已经设置的属性
			values.remove("type");
			values.remove("driverClassName");
			values.remove("url");
			values.remove("username");
			values.remove("password");
			dataSourcePropertyValues = new MutablePropertyValues(values);
		}
		dataBinder.bind(dataSourcePropertyValues);
	}
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		System.out.println("DynamicDataSourceRegister.registerBeanDefinitions()");
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		// 将主数据源添加到更多数据源中
		targetDataSources.put("write", defaultWriteDataSource);
		DataSourceRwContext.put("write");
		// 将读数据源添加到更多数据源中
		targetDataSources.put("read", readDataSource);
		DataSourceRwContext.put("read");
		// 添加更多数据源
		targetDataSources.putAll(multiDataSources);
		for (String key : multiDataSources.keySet()) {
			DataSourceRwContext.put(key);
		}
		// 创建DynamicDataSource
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(RwDataSource.class);
		beanDefinition.setSynthetic(true);
		MutablePropertyValues mpv = beanDefinition.getPropertyValues();
		// 添加属性：AbstractRoutingDataSource.defaultTargetDataSource
		mpv.addPropertyValue("defaultTargetDataSource", defaultWriteDataSource);
		mpv.addPropertyValue("targetDataSources", targetDataSources);
		registry.registerBeanDefinition("dataSource", beanDefinition);
	}
}
