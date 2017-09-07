/**
 * @version 0.0.1-SNAPSHOT 5/09/2017
 * @since 0.0.1-SNAPSHOT 5/09/2017
 */
package com.bachue.filepropertymavenplugin.util;

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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.logging.Log;

/**
 * File utility class
 * @author Alejandro Vivas
 * @version 5/09/2017 0.0.1-SNAPSHOT
 * @since 5/09/2017 0.0.1-SNAPSHOT
 */
public final class FileUtil
{
	/**
	 * To avoid instances
	 * @author Alejandro Vivas
	 * @version 5/09/2017 0.0.1-SNAPSHOT
	 * @since 5/09/2017 0.0.1-SNAPSHOT
	 */
	private FileUtil()
	{
	}

	/**
	 * Finds files within a given list directories (and sub directories) which match an list of extensions.
	 * @author Alejandro Vivas
	 * @version 5/09/2017 0.0.1-SNAPSHOT
	 * @since 5/09/2017 0.0.1-SNAPSHOT
	 * @param directories List of directories to find files
	 * @param extensions list of extensions
	 * @return Collection with extension files
	 */
	public static Collection<File> getFiles(List<String> directories, List<String> extensions, Log log)
	{
		List<File> files = new ArrayList<>();
		String[] arrayExtensions = extensions.toArray(new String[extensions.size()]);
		for (String directory : directories)
		{
			File fileDirectory = new File(directory);
			if (fileDirectory.exists())
			{
				files.addAll(getFiles(fileDirectory, arrayExtensions));
			}
			else
			{
				log.warn("Invalid directory:[" + directory + "]");
			}
		}

		return files;
	}

	/**
	 * Finds files within a given a directory (and sub directories) which match an array of extensions.
	 * @author Alejandro Vivas
	 * @version 5/09/2017 0.0.1-SNAPSHOT
	 * @since 5/09/2017 0.0.1-SNAPSHOT
	 * @param directory Directories to find files
	 * @param extensions Array of extensions
	 * @return Collection with extension files
	 */
	public static Collection<File> getFiles(File directory, String[] extensions)
	{
		return FileUtils.listFiles(directory, extensions, true);
	}

	/**
	 * Read file and return content in String
	 * @author Alejandro Vivas
	 * @version 6/09/2017 0.0.1-SNAPSHOT
	 * @since 6/09/2017 0.0.1-SNAPSHOT
	 * @param path Path to file
	 * @param encoding Encoding to create String
	 * @return String with content
	 * @throws IOException If fail to read file
	 */
	public static String fileToString(String path, String encoding) throws IOException
	{
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
