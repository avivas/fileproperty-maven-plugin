/**
 * @author Alejandro Vivas
 * @version 0.0.1-SNAPSHOT 6/09/2017
 * @since 0.0.1-SNAPSHOT 6/09/2017
 */
package com.bachue.filepropertymavenplugin;

/*-
 * #%L
 * fileproperty-maven-plugin Maven Plugin
 * %%
 * Copyright (C) 2017 Bachue
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Object with configuration
 * @author Alejandro Vivas
 * @version 7/09/2017 0.0.1-SNAPSHOT
 * @since 6/09/2017 0.0.1-SNAPSHOT
 */
public class LoadProperty
{
	/** Property name in maven */
	private String			propertyName;
	/** List with sources files */
	private List<String>	sourceFiles;
	/** List with source directories */
	private List<String>	sourceDirectories;
	/** Extensions filter */
	private List<String>	extensions;
	/** Source encoding */
	private String			sourceEncoding	= StandardCharsets.UTF_8.toString();
	/** If is true append a new line before append content */
	private boolean			appendNewLine	= true;
	/** Prepend content */
	private String prependContent;
	/** Append content */
	private String appendContent;

	/**
	 * @author Alejandro Vivas
	 * @version 6/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @return the propertyName
	 */
	public String getPropertyName()
	{
		return propertyName;
	}

	/**
	 * @author Alejandro Vivas
	 * @version 6/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @param propertyName the propertyName to set
	 */
	public void setPropertyName(String propertyName)
	{
		this.propertyName = propertyName;
	}

	/**
	 * @author Alejandro Vivas
	 * @version 6/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @return the sourceFiles
	 */
	public List<String> getSourceFiles()
	{
		return sourceFiles;
	}

	/**
	 * @author Alejandro Vivas
	 * @version 6/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @param sourceFiles the sourceFiles to set
	 */
	public void setSourceFiles(List<String> sourceFiles)
	{
		this.sourceFiles = sourceFiles;
	}

	/**
	 * @author Alejandro Vivas
	 * @version 6/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @return the sourceDirectories
	 */
	public List<String> getSourceDirectories()
	{
		return sourceDirectories;
	}

	/**
	 * @author Alejandro Vivas
	 * @version 6/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @param sourceDirectories the sourceDirectories to set
	 */
	public void setSourceDirectories(List<String> sourceDirectories)
	{
		this.sourceDirectories = sourceDirectories;
	}

	/**
	 * @author Alejandro Vivas
	 * @version 6/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @return the extensions
	 */
	public List<String> getExtensions()
	{
		return extensions;
	}

	/**
	 * @author Alejandro Vivas
	 * @version 6/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @param extensions the extensions to set
	 */
	public void setExtensions(List<String> extensions)
	{
		this.extensions = extensions;
	}

	/**
	 * @author Alejandro Vivas
	 * @version 6/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @param sourceEncoding the sourceEncoding to set
	 */
	public void setSourceEncoding(String sourceEncoding)
	{
		this.sourceEncoding = sourceEncoding;
	}

	/**
	 * @author Alejandro Vivas
	 * @version 6/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @return the sourceEncoding
	 */
	public String getSourceEncoding()
	{
		return sourceEncoding;
	}
	
	/**
	 * @author Alejandro Vivas
	 * @version 6/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @param appendNewLine the appendNewLine to set
	 */
	public void setAppendNewLine(boolean appendNewLine)
	{
		this.appendNewLine = appendNewLine;
	}
	
	/**
	 * @author Alejandro Vivas
	 * @version 6/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @return the appendNewLine
	 */
	public boolean isAppendNewLine()
	{
		return appendNewLine;
	}

	/**
	 * @author Alejandro Vivas
	 * @version 7/09/2017 0.0.1-SNAPSHOT
	 * @since 7/09/2017 0.0.1-SNAPSHOT
	 * @return the prependContent
	 */
	public String getPrependContent()
	{
		return prependContent;
	}

	/**
	 * @author Alejandro Vivas
	 * @version 7/09/2017 0.0.1-SNAPSHOT
	 * @since 7/09/2017 0.0.1-SNAPSHOT
	 * @param prependContent the prependContent to set
	 */
	public void setPrependContent(String prependContent)
	{
		this.prependContent = prependContent;
	}

	/**
	 * @author Alejandro Vivas
	 * @version 7/09/2017 0.0.1-SNAPSHOT
	 * @since 7/09/2017 0.0.1-SNAPSHOT
	 * @return the appendContent
	 */
	public String getAppendContent()
	{
		return appendContent;
	}

	/**
	 * @author Alejandro Vivas
	 * @version 7/09/2017 0.0.1-SNAPSHOT
	 * @since 7/09/2017 0.0.1-SNAPSHOT
	 * @param appendContent the appendContent to set
	 */
	public void setAppendContent(String appendContent)
	{
		this.appendContent = appendContent;
	}
}
