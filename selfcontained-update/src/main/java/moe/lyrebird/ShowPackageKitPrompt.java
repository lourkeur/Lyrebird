package moe.lyrebird;

import moe.lyrebird.model.update.selfupdate.linux.PackageKitService;

public class ShowPackageKitPrompt {
	public static void main(String[] args) {
		new PackageKitService().showNativeUnixPackageKitPrompt();
	}
}
