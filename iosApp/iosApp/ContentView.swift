import SwiftUI
import Shared
import SafariServices
import WebKit

struct WebView: UIViewRepresentable {
    let url: URL

    func makeUIView(context: Context) -> WKWebView {
        return WKWebView()
    }

    func updateUIView(_ uiView: WKWebView, context: Context) {
        let request = URLRequest(url: url)
        uiView.load(request)
    }
}

struct SafariView: UIViewControllerRepresentable {
    let url: URL

    func makeUIViewController(context: Context) -> SFSafariViewController {
        return SFSafariViewController(url: url)
    }

    func updateUIViewController(_ uiViewController: SFSafariViewController, context: Context) {}
}


struct ContentView: View {
    @State private var showingSafariView = false
    @State private var safariURL: URL?
    @State private var webViewURL: URL?

    var body: some View {
        VStack(spacing: 20) {
            Button(action: {
                webViewURL = URL(string: "https://ginyolith.github.io/permission-req-kmm-sample/")
            }) {
                Text("Open in WebView")
                    .padding()
                    .background(Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(8)
            }

            Button(action: {
                safariURL = URL(string: "https://ginyolith.github.io/permission-req-kmm-sample/")
                showingSafariView = true
            }) {
                Text("Open in Safari View Controller")
                    .padding()
                    .background(Color.green)
                    .foregroundColor(.white)
                    .cornerRadius(8)
            }
            .sheet(isPresented: $showingSafariView) {
                if let url = safariURL {
                    SafariView(url: url)
                }
            }

            Button(action: {
                if let url = URL(string: "https://ginyolith.github.io/permission-req-kmm-sample/") {
                    UIApplication.shared.open(url)
                }
            }) {
                Text("Open in Browser")
                    .padding()
                    .background(Color.red)
                    .foregroundColor(.white)
                    .cornerRadius(8)
            }

            if let webViewURL = webViewURL {
                WebView(url: webViewURL)
                    .frame(height: 400)
            }
        }
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
