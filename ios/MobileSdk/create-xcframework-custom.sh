

xcodebuild archive  -project MobileSdk.xcodeproj -scheme MobileSdk -destination "generic/platform=iOS" -archivePath "archives/MobileSdk-iOS" SKIP_INSTALL=NO BUILD_LIBRARY_FOR_DISTRIBUTION=YES
xcodebuild archive  -project MobileSdk.xcodeproj -scheme MobileSdk -destination "generic/platform=iOS Simulator" -archivePath "archives/MobileSdk-iOS_Simulator" SKIP_INSTALL=NO BUILD_LIBRARY_FOR_DISTRIBUTION=YES

# MODULES_PATH="archive/MobileSdk-iOS.xcarchive/Products/usr/local/lib/MobileSdk.framework/Modules"
# mkdir -p $MODULES_PATH
# cp -a $BUILD_PRODUCTS_PATH/Release-iphoneos/Emoji.swiftmodule $MODULES_PATH


xcodebuild -create-xcframework -archive archives/MobileSdk-iOS.xcarchive -framework MobileSdk.framework -archive archives/MobileSdk-iOS_Simulator.xcarchive -framework MobileSdk.framework -output xcframeworks/MobileSdk.xcframework