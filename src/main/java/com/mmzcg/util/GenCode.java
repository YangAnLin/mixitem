package com.mmzcg.util;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

public class GenCode {

    private static final String TABLE_NAMES = "roles";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        // 实体属性 Swagger2 注解
        gc.setSwagger2(true);
        // 是谁生成的
        gc.setAuthor("anthony");
        gc.setFileOverride(true);

        gc.setEnableCache(true);// XML 二级缓存
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        // activeRecord 实体类继承Model类
        gc.setActiveRecord(true);
        // XML ResultMap
        gc.setBaseResultMap(true);


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://10.14.2.50:3306/mixitem?noAccessToProcedureBodies=true&useSSL=false&serverTimezone=Asia/Shanghai&useSSL=false");
        dsc.setSchemaName("mixitem");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                if (fieldType.toLowerCase().contains( "decimal" ) ) {
                    return DbColumnType.BIG_DECIMAL;
                }
                if (fieldType.toLowerCase().contains( "datetime" ) ) {
                    return DbColumnType.DATE;
                }
                if (fieldType.toLowerCase().contains( "date" ) ) {
                    return DbColumnType.DATE;
                }
                if (fieldType.toLowerCase().contains( "tinyint" ) ) {
                    return DbColumnType.INTEGER;
                }
                if (fieldType.toLowerCase().contains( "bit" ) ) {
                    return DbColumnType.INTEGER;
                }
                if (fieldType.toLowerCase().contains( "smallint" ) ) {
                    return DbColumnType.INTEGER;
                }

                return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
            }
        });

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.mmzcg");

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 链式
        strategy.setEntityLombokModel(true);
        // 需要生成的表名
        strategy.setInclude(TABLE_NAMES);
        // 驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        // 是否生成实体时，生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
//        strategy.setTablePrefix("tb_");  //需要截取不用的，比如我的表开头都有tb_,需要去掉后生成
        // 是否跳过视图
        strategy.setSkipView(false);



        mpg.setGlobalConfig(gc);
        mpg.setDataSource(dsc);
        mpg.setPackageInfo(pc);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }
}
