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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import com.bachue.filepropertymavenplugin.util.FileUtil;

/**
 * Load content file to properties in maven.
 * @author Alejandro Vivas
 * @version 7/09/2017 0.0.1-SNAPSHOT
 * @since 6/09/2017 0.0.1-SNAPSHOT
 */
@Mojo(name = "load", defaultPhase = LifecyclePhase.VALIDATE)
public class LoadMojo extends AbstractMojo
{
	/** Object to get project path */
	@Parameter(defaultValue = "${project}", readonly = true, required = true)
	private MavenProject		project;
	/** Object with configuration */
	@Parameter(defaultValue = "${loadProperties}", required = false)
	private List<LoadProperty>	loadProperties;
	
	/**
	 * @author Alejandro Vivas
	 * @version 6/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @see org.apache.maven.plugin.Mojo#execute()
	 */
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException
	{
		for (LoadProperty loadProperty : this.getLoadProperties())
		{
			StringBuilder stringBuilder = new StringBuilder();
			readContentSourceFiles(loadProperty, stringBuilder);
			readContentSourceDirectories(loadProperty, stringBuilder);

			this.getProject().getProperties().put(loadProperty.getPropertyName(), stringBuilder.toString());
		}
	}

	/**
	 * Read content of sources file
	 * @author Alejandro Vivas
	 * @version 7/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @param loadProperty Object with configuration
	 * @param stringBuilder Object with content
	 * @throws MojoExecutionException If fail to read a file
	 */
	private void readContentSourceFiles(LoadProperty loadProperty, StringBuilder stringBuilder) throws MojoExecutionException
	{
		if (loadProperty.getSourceFiles() != null)
		{
			for (String sourceFile : loadProperty.getSourceFiles())
			{
				if(!new File(sourceFile).isAbsolute())
				{
					sourceFile = getProject().getBasedir() + File.separator + sourceFile;
				}
				readContent(sourceFile, loadProperty.getSourceEncoding(), loadProperty.isAppendNewLine(), stringBuilder,loadProperty.getPrependContent(),loadProperty.getAppendContent());
			}
		}
	}

	/**
	 * Read content of sources directories
	 * @author Alejandro Vivas
	 * @version 7/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @param loadProperty Object with configuration
	 * @param stringBuilder Object with content
	 * @throws MojoExecutionException If fail to read a file
	 */
	private void readContentSourceDirectories(LoadProperty loadProperty, StringBuilder stringBuilder) throws MojoExecutionException
	{
		if (loadProperty.getSourceDirectories() != null)
		{
			List<String> absoluteDirectories = new ArrayList<>(loadProperty.getSourceDirectories().size());
			for(String sourceDirectory: loadProperty.getSourceDirectories())
			{
				if(new File(sourceDirectory).isAbsolute())
				{
					absoluteDirectories.add(sourceDirectory);
				}
				else
				{
					absoluteDirectories.add(getProject().getBasedir() + File.separator + sourceDirectory);
				}
			}
			
			Collection<File> files = FileUtil.getFiles(absoluteDirectories, loadProperty.getExtensions(), getLog());
			for (File sourceFile : files)
			{
				readContent(sourceFile.getAbsolutePath(), loadProperty.getSourceEncoding(), loadProperty.isAppendNewLine(), stringBuilder,loadProperty.getPrependContent(),loadProperty.getAppendContent());
			}
		}
	}

	/**
	 * Read content of a file
	 * @author Alejandro Vivas
	 * @version 7/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @param sourceFile Path of source file
	 * @param sourceEncoding Source encoding
	 * @param isAppendNewLine true if append a new line before add new file and stringBuilder is not empty
	 * @param stringBuilder Object with content
	 * @throws MojoExecutionException If fail to read a file
	 */
	private void readContent(String sourceFile, String sourceEncoding, boolean isAppendNewLine, StringBuilder stringBuilder,String prependContent,String appendContent) throws MojoExecutionException
	{
		String content;
		try
		{
			String fileName = sourceFile.substring(sourceFile.lastIndexOf(File.separator)+1);
			String simpleFileName = sourceFile.substring(sourceFile.lastIndexOf(File.separator)+1,sourceFile.lastIndexOf("."));
			content = FileUtil.fileToString(sourceFile, sourceEncoding);
			
			if(prependContent != null)
			{
				content = prependContent + content;
			}
			if(appendContent != null)
			{
				content = content + appendContent;
			}
			
			content = content.replaceAll("\\$\\{fileName\\}", fileName);
			content = content.replaceAll("\\$\\{simpleFileName\\}", simpleFileName);
			content = content.replaceAll("\\$\\{absoluteFileName\\}", sourceFile);
		}
		catch (IOException e)
		{
			throw new MojoExecutionException("Error to read file:[" + sourceFile + "]");
		}
		if ((stringBuilder.length() != 0) && isAppendNewLine)
		{
			stringBuilder.append('\n');
		}
		stringBuilder.append(content);
	}
	
	/**
	 * @author Alejandro Vivas
	 * @version 6/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @param project the project to set
	 */
	public void setProject(MavenProject project)
	{
		this.project = project;
	}
	
	/**
	 * @author Alejandro Vivas
	 * @version 6/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @return the project
	 */
	public MavenProject getProject()
	{
		return project;
	}
	
	/**
	 * @author Alejandro Vivas
	 * @version 6/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @param loadProperties the loadProperties to set
	 */
	public void setLoadProperties(List<LoadProperty> loadProperties)
	{
		this.loadProperties = loadProperties;
	}
	
	/**
	 * @author Alejandro Vivas
	 * @version 6/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @return the loadProperties
	 */
	public List<LoadProperty> getLoadProperties()
	{
		return loadProperties;
	}
}
