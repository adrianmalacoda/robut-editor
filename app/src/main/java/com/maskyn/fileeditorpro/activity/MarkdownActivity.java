/*
 * Copyright (C) 2014 Vlad Mihalachi
 * Copyright (C) 2018 Adrian Malacoda
 *
 * This file is part of Text Editor 8000.
 *
 * Text Editor 8000 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Text Editor 8000 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.maskyn.fileeditorpro.activity;

import android.content.Context;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class MarkdownActivity extends Activity {
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		MarkdownView webView = new MarkdownView(this);
		setContentView(webView);
		webView.loadMarkdown(getIntent().getStringExtra("text"), "file:///android_asset/classic_theme_markdown.css");
	}

  private static class MarkdownView extends WebView {
    private final Parser parser;
    private final HtmlRenderer renderer;

    MarkdownView (Context context) {
      super(context);
      parser = Parser.builder().build();
      renderer = HtmlRenderer.builder().build();
    }

    public void loadMarkdown(String content, String cssUri) {
      loadData(renderer.render(parser.parse(content)), "text/html", null);
    }
  }
}
