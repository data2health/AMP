  /*Copyright (C) 2014  JD Software, Inc.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
  */
package com.jd.survey.web;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.converter.Converter;

import com.jd.survey.GlobalSettings;
import com.jd.survey.domain.settings.Day;
import com.jd.survey.domain.settings.GroupingOperator;
import com.jd.survey.domain.settings.LogicOperator;
import com.jd.survey.domain.settings.Sector;
import com.jd.survey.domain.settings.SurveyTemplate;
import com.jd.survey.domain.survey.Survey;
import com.jd.survey.service.survey.SurveyService;

import com.jd.survey.service.settings.ApplicationSettingsService;
import com.jd.survey.service.settings.SurveySettingsService;


/**
 * A central place to register survey converters and formatters. 
 */
@Configurable
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean{
	private static final Log log = LogFactory.getLog(ApplicationConversionServiceFactoryBean.class);

	@Autowired  private SurveyService surveyService;
	@Autowired	private ApplicationContext applicationContext;
	@Autowired	private ApplicationSettingsService applicationSettingsService;
	@Autowired	private SurveySettingsService surveySettingsService;
	
	
	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register survey converters and formatters
	}
	

	public Converter<Survey, String> getSurveyToStringConverter() {
        return new Converter<Survey, java.lang.String>() {
            public String convert(Survey surveyDefinition) {
            	log.info("converting SurveyToString");
            	return new StringBuilder().append(surveyDefinition.getTypeName()).toString();
            }
        };
    }
    public Converter<Long, Survey> getIdToSurveyConverter() {
        return new Converter<java.lang.Long,  Survey>() {
            public  Survey convert(java.lang.Long id) {
            	log.info("converting Long to Survey id=" + id + " result" + surveyService.survey_findById(id).toString());
                return surveyService.survey_findById(id);
            }
        };
    }
    public Converter<String, Survey> getStringToSurveyConverter() {
        return new Converter<java.lang.String, Survey>() {
            public Survey convert(String id) {
            	log.info("converting String to Survey id=" + id);
                return getObject().convert(getObject().convert(id, Long.class), Survey.class);
            }
        };
    }

    public Converter<GroupingOperator, String> geGroupingOperatorToStringConverter() {
        return new Converter<GroupingOperator, java.lang.String>() {
            public String convert(GroupingOperator groupingOperator) {
            	log.info("converting QuestionTypeToString");
            	return groupingOperator.getCode();
            }
        };
    }
    public Converter<String, GroupingOperator> getStringToGroupingOperatorConverter() {
        return new Converter<java.lang.String, GroupingOperator>() {
            public GroupingOperator convert(String id) {
            	log.info("converting String to Question type id=" + id);
                return GroupingOperator.fromCode(id);
            }
        };
    }
    public Converter<LogicOperator, String> getLogicOperatorToStringConverter() {
        return new Converter<LogicOperator, java.lang.String>() {
            public String convert(LogicOperator logicOperator) {
            	log.info("converting QuestionTypeToString");
            	return logicOperator.getCode();
            }
        };
    }
    public Converter<String, LogicOperator> getStringLogicOperatorTypeConverter() {
        return new Converter<java.lang.String, LogicOperator>() {
            public LogicOperator convert(String id) {
            	log.info("converting String to Question type id=" + id);
                return LogicOperator.fromCode(id);
            }
        };
    }
    public Converter<Sector, String> getSectorToStringConverter() {
        return new Converter<Sector, java.lang.String>() {
            public String convert(Sector sector) {
            	log.info("converting SectorToString");
            	return new StringBuilder().append(sector.getName()).toString();
            }
        };
    }
    public Converter<Long, Sector> getIdToSectorConverter() {
        return new Converter<java.lang.Long,  Sector>() {
            public  Sector convert(java.lang.Long id) {
            	log.info("converting Long to Sector id=" + id + " result" + surveySettingsService.sector_findById(id));
                return surveySettingsService.sector_findById(id);
            }
        };
    }
    public Converter<String, Sector> getStringToSectorConverter() {
        return new Converter<java.lang.String, Sector>() {
            public Sector convert(String id) {
            	log.info("converting String to Sector id=" + id);
                return getObject().convert(getObject().convert(id, Long.class), Sector.class);
            }
        };
    }
    
    public Converter<SurveyTemplate, String> getSurveyTemplateToStringConverter() {
        return new Converter<SurveyTemplate, java.lang.String>() {
            public String convert(SurveyTemplate surveyTemplate) {
            	log.info("converting SurveyTemplateToString");
            	return new StringBuilder().append(surveyTemplate.getName()).toString();
            }
        };
    }
    public Converter<Long, SurveyTemplate> getIdToSurveyTemplateConverter() {
        return new Converter<java.lang.Long,  SurveyTemplate>() {
            public  SurveyTemplate convert(java.lang.Long id) {
            	log.info("converting Long to SurveyTemplate id=" + id + " result" + surveySettingsService.surveyTemplate_findById(id));
                return surveySettingsService.surveyTemplate_findById(id);
            }
        };
    }
    public Converter<String, SurveyTemplate> getStringToSurveyTemplateConverter() {
        return new Converter<java.lang.String, SurveyTemplate>() {
            public SurveyTemplate convert(String id) {
            	log.info("converting String to SurveyTemplate id=" + id);
                return getObject().convert(getObject().convert(id, Long.class), SurveyTemplate.class);
            }
        };
    }
    
    public Converter<Day, String> getDayToStringConverter() {
        return new Converter<Day, java.lang.String>() {
            public String convert(Day day) {
            	return new StringBuilder().append(day.getDayName()).toString();
            }
        };
    }
    public Converter<Long, Day> getIdToDayConverter() {
        return new Converter<java.lang.Long,  Day>() {
            public  Day convert(java.lang.Long id) {
                return surveySettingsService.day_findById(id);
            }
        };
    }
    public Converter<String, Day> getStringToDayConverter() {
        return new Converter<java.lang.String, Day>() {
            public Day convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Day.class);
            }
        };
    }
    
    public Converter<GlobalSettings, String> getGlobalSettingsToStringConverter() {
        return new Converter<GlobalSettings, java.lang.String>() {
            public String convert(GlobalSettings globalSettings) {
            	log.info("converting DepartmentToString");
            	return new StringBuilder().append(globalSettings.getPasswordEnforcementRegex()).toString();
            }
        };
    }
    public Converter<Long, GlobalSettings> getIdToGlobalSettingsConverter() {
        return new Converter<java.lang.Long,  GlobalSettings>() {
            public  GlobalSettings convert(java.lang.Long id) {
            	log.info("converting Long to globalSettings id=" + id + " result" + applicationSettingsService.globalSettings_findById(id).toString());
                return applicationSettingsService.globalSettings_findById(id);
            }
        };
    }
    public Converter<String, GlobalSettings> getStringToGlobalSettingsConverter() {
        return new Converter<java.lang.String, GlobalSettings>() {
            public GlobalSettings convert(String id) {
            	log.info("converting String to Department id=" + id);
                return getObject().convert(getObject().convert(id, Long.class), GlobalSettings.class);
            }
        };
    }

    public void installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getSurveyToStringConverter());
        registry.addConverter(getIdToSurveyConverter());
        registry.addConverter(getStringToSurveyConverter());
        
        //custom DateTimeFormatter
        DateTimeFormatAnnotationFormatterFactory dateTimeFormatAnnotationFormatterFactory = new DateTimeFormatAnnotationFormatterFactory();
		dateTimeFormatAnnotationFormatterFactory.setApplicationContext(applicationContext);
		registry.addFormatterForFieldAnnotation(dateTimeFormatAnnotationFormatterFactory);
    
		registry.addConverter(geGroupingOperatorToStringConverter()); 
		registry.addConverter(getStringToGroupingOperatorConverter()); 
		registry.addConverter(getLogicOperatorToStringConverter()); 
		registry.addConverter(getStringLogicOperatorTypeConverter()); 

		registry.addConverter(getSectorToStringConverter());
        registry.addConverter(getIdToSectorConverter());
        registry.addConverter(getStringToSectorConverter());
		
        registry.addConverter(getSurveyTemplateToStringConverter());
        registry.addConverter(getIdToSurveyTemplateConverter());
        registry.addConverter(getStringToSurveyTemplateConverter());
		
               
        registry.addConverter(getDayToStringConverter());
        registry.addConverter(getIdToDayConverter());
        registry.addConverter(getStringToDayConverter());
        
        registry.addConverter(getGlobalSettingsToStringConverter());
        registry.addConverter(getIdToGlobalSettingsConverter());
        registry.addConverter(getStringToGlobalSettingsConverter());
    }
    
    
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
	
	
    




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
