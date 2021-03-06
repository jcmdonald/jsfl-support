package org.jsflsupport;

import com.intellij.lang.javascript.library.JSPredefinedLibraryProvider;
import com.intellij.openapi.roots.libraries.scripting.ScriptingLibraryModel;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.ArrayUtil;
import com.intellij.util.containers.HashSet;

import java.net.URL;
import java.util.Set;

/*
 * Copyright 2011 Evgeniy Polyakov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class JSFLPredefinedLibraryProvider extends JSPredefinedLibraryProvider {

    private static final String NAME = "JSFL";

    private static final String FILES[] = {
            "/org/jsflsupport/libraries/JSFLDocument.js",
            "/org/jsflsupport/libraries/JSFLDrawing.js",
            "/org/jsflsupport/libraries/JSFLElements.js",
            "/org/jsflsupport/libraries/JSFLGeom.js",
            "/org/jsflsupport/libraries/JSFLItems.js",
            "/org/jsflsupport/libraries/JSFLTimeline.js",
            "/org/jsflsupport/libraries/JSFLTopLevel.js"
    };

    public ScriptingLibraryModel[] getPredefinedLibraries() {
        Set<VirtualFile> libFiles = getFiles();
        return new ScriptingLibraryModel[]{
                new ScriptingLibraryModel(NAME,
                                          libFiles.toArray(new VirtualFile[libFiles.size()]),
                                          VirtualFile.EMPTY_ARRAY,
                                          ArrayUtil.EMPTY_STRING_ARRAY,
                                          true)};
    }

    public Set<VirtualFile> getPredefinedLibraryFiles() {
        return getFiles();
    }

    private static Set<VirtualFile> getFiles() {
        Set<VirtualFile> libFiles = new HashSet<VirtualFile>();
        for (String fileName : FILES) {
            VirtualFile file = getPredefinedLibFile(fileName);
            if (file != null)
                libFiles.add(file);
        }
        return libFiles;
    }

    private static VirtualFile getPredefinedLibFile(String libFileName) {
        URL libFileUrl = JSFLPredefinedLibraryProvider.class.getResource(libFileName);
        return VfsUtil.findFileByURL(libFileUrl);
    }
}