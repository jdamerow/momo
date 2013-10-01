package edu.asu.momo.valid;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.asu.momo.web.request.backing.TimeChangeRequestBean;

public class GroupCompleteValidator implements
		ConstraintValidator<GroupCompleteCheck, TimeChangeRequestBean> {

	private static final Logger logger = LoggerFactory.getLogger(GroupCompleteValidator.class);
	private String[] messages;
	
	@Override
	public void initialize(GroupCompleteCheck arg0) {
		messages = arg0.groupMessages();
	}

	@Override
	public boolean isValid(TimeChangeRequestBean arg0,
			ConstraintValidatorContext arg1) {
		
		boolean isValid = true;
		
		int i = 0;
		for (String msg : messages) {
			Map<String, String> values = getValue(i, arg0);
			boolean oneIsNotEmpty = false;
			boolean oneIsEmpty = false;
			
			List<String> emptyFields = new ArrayList<String>();
			for (String key : values.keySet()) {
				String value = values.get(key);
				if (value == null || value.trim().isEmpty()) {
					emptyFields.add(key);
					oneIsEmpty = true;
				}
				else
					oneIsNotEmpty = true;
			}
			
			/*
			 * If there is at least one given, and one not, that's an error.
			 */
			if (oneIsNotEmpty && oneIsEmpty) {
				for (String empty : emptyFields) {
					arg1
		            .buildConstraintViolationWithTemplate( msg )
		            .addNode( empty ).addConstraintViolation();
				}
				
				isValid = false;
			}
			
			i++;
		}
		
		return isValid;
	}

	protected Map<String, String> getValue(int index, TimeChangeRequestBean bean) {
		Map<String, String> values = new HashMap<String, String>();
		for (Field field : bean.getClass().getDeclaredFields()) {
			Annotation[] annotations = field.getDeclaredAnnotations();
			for (Annotation an : annotations) {
				if (an instanceof GroupComplete) {
					if (((GroupComplete) an).groupId() == index) {
						field.setAccessible(true);
						try {
							values.put(field.getName(), field.get(bean).toString());
						} catch (IllegalArgumentException e) {
							logger.error("Couldn't retrieve value", e);
						} catch (IllegalAccessException e) {
							logger.error("Couldn't retrieve value", e);
						}
					}
				}
			}
		}
		return values;
	}
}
