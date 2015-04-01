/*
 * RSConnectPublishSource.java
 *
 * Copyright (C) 2009-15 by RStudio, Inc.
 *
 * Unless you have received this program directly from RStudio pursuant
 * to the terms of a commercial license agreement with RStudio, then
 * this program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */
package org.rstudio.studio.client.rsconnect.model;

import org.rstudio.core.client.StringUtil;
import org.rstudio.core.client.files.FileSystemItem;

public class RSConnectPublishSource
{
   public RSConnectPublishSource(String sourceFile)
   {
      this(sourceFile, sourceFile);
   }
   
   public RSConnectPublishSource(String sourceFile, String outputFile)
   {
      deployFile_ = outputFile;
      sourceFile_ = sourceFile;
      deployDir_ = FileSystemItem.createFile(outputFile).getParentPathString();
   }
   
   public RSConnectPublishSource(RenderedDocPreview preview)
   {
      deployFile_ = preview.getOutputFile();
      sourceFile_ = preview.getSourceFile();
      deployDir_ = FileSystemItem.createFile(preview.getOutputFile())
            .getParentPathString();
   }
   
   public RSConnectPublishSource(String sourceFile, String deployDir, 
         String deployFile)
   {
      sourceFile_ = sourceFile;
      deployDir_ = deployDir;
      deployFile_ = deployFile;
   }
   
   public String getDeployFile()
   {
      return deployFile_;
   }
   
   public String getDeployDir()
   {
      return deployDir_;
   }
   
   public String getSourceFile()
   {
      return sourceFile_;
   }
   
   public boolean isSourceExt(String ext)
   {
       return StringUtil.getExtension(deployFile_).toLowerCase().equals(ext);
   }
   
   public boolean isDocument()
   {
      return isSourceExt("rmd") || isSourceExt("html");
   }
   
   public String getDeployKey()
   {
      return isDocument() ? getSourceFile() : getDeployDir();
   }
   
   private final String deployFile_;
   private final String deployDir_;
   private final String sourceFile_;
}
