package com.ginyolith.kmpdemo

import TEST_PAGE_URL
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.FileProvider
import java.io.File

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    private fun openInWebView(url: String) {
        setContent {
            WebViewScreen(url)
        }
    }

    private fun openInChromeCustomTab(url: String) {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }

    private fun openInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(intent)
    }

    @Composable
    fun MainScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Button(
                onClick = { openInWebView(TEST_PAGE_URL) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text("Open in WebView")
            }
            Button(
                onClick = { openInChromeCustomTab(TEST_PAGE_URL) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text("Open in Chrome Custom Tab")
            }
            Button(
                onClick = { openInBrowser(TEST_PAGE_URL) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text("Open in Browser")
            }
        }
    }

    @Composable
    fun WebViewScreen(url: String) {
        AndroidView(factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        }, modifier = Modifier.fillMaxSize())
    }

    @Preview
    @Composable
    fun PreviewMainScreen() {
        MainScreen()
    }
}
