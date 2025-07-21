import UIKit
import SwiftUI
import Deletio

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}

struct ContentView: View {
    private let dynamicBackgroundColor = UIColor { (traitCollection: UITraitCollection) -> UIColor in
        if traitCollection.userInterfaceStyle == .dark {
            return UIColor(hex: 0x1a1829) // Your dark theme background
        } else {
            return UIColor(hex: 0xF5F3F7) // Your light theme background
        }
    }
    var body: some View {
        ComposeView()
            .background(Color(dynamicBackgroundColor)).ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}






