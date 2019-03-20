/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.product.definitions.web.internal.servlet.taglib.ui;

import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.servlet.taglib.ui.BaseJSPFormNavigatorEntry;
import com.liferay.portal.kernel.servlet.taglib.ui.FormNavigatorEntry;
import com.liferay.taglib.util.CustomAttributesUtil;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ethan Bustad
 */
@Component(
	property = "form.navigator.entry.order:Integer=10",
	service = FormNavigatorEntry.class
)
public class CPAttachmentFileEntryDetailsCustomFieldsFormNavigatorEntry
	extends BaseJSPFormNavigatorEntry<CPAttachmentFileEntry> {

	@Override
	public String getCategoryKey() {
		return CPAttachmentFileEntryFormNavigatorConstants.
			CATEGORY_KEY_CP_ATTACHMENT_FILE_ENTRY_DETAILS;
	}

	@Override
	public String getFormNavigatorId() {
		return CPAttachmentFileEntryFormNavigatorConstants.
			FORM_NAVIGATOR_ID_CP_ATTACHMENT_FILE_ENTRY;
	}

	@Override
	public String getKey() {
		return "custom-fields";
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, getKey());
	}

	@Override
	public boolean isVisible(
		User user, CPAttachmentFileEntry cpDefinitionSpecificationOptionValue) {

		boolean hasCustomAttributesAvailable = false;

		try {
			long classPK = 0;

			if (cpDefinitionSpecificationOptionValue != null) {
				classPK =
					cpDefinitionSpecificationOptionValue.
						getCPAttachmentFileEntryId();
			}

			hasCustomAttributesAvailable =
				CustomAttributesUtil.hasCustomAttributes(
					user.getCompanyId(), CPAttachmentFileEntry.class.getName(),
					classPK, null);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}

		return hasCustomAttributesAvailable;
	}

	@Override
	protected String getJspPath() {
		return "/attachment_file_entry/custom_fields.jsp";
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CPAttachmentFileEntryDetailsCustomFieldsFormNavigatorEntry.class);

}