# Vulnerable APK based on OWASP MSTG

This repository contains a vulnerable APK (Android application package) created for educational purposes based on the OWASP MSTG (Mobile Security Testing Guide). The purpose of this APK is to help developers and security enthusiasts learn about mobile application security vulnerabilities.

## Prerequisites

To run the vulnerable APK, you need the following:

- An Android device or emulator running Android OS (version 7.0 (SDK 24) or higher)
- [ADB (Android Debug Bridge)](https://developer.android.com/studio/command-line/adb) installed and configured on your machine
- [Frida](https://frida.re/docs/installation/)
- [Docker](https://docs.docker.com/engine/install/)

## Installation

1. Clone this repository to your local machine:

   `git clone https://github.com/satmul/vulnapk.git`
   or you could install the APK directly from release, but it's strongly recommended to clone this repo.
   
2. Install docker on your host system
3. Initialize the web:
  ```
  cd web
  docker-compose up -d
  ```
4. Web will be running on port 3000 (default)
5. Install the APK, move to settings, add your `docker_ip:3000` and save to access the InsecureWeb section

## Usage
Once the VulnAPK is installed and launched on your device/emulator, you can explore and test the various security vulnerabilities implemented in the application. It is recommended to use this application in a controlled environment for learning purposes only and not on production devices.

## Disclaimer
VulnAPK is created for educational purposes only. Use it responsibly and do not install or run it on any device without proper authorization. The creators and contributors of this repository are not responsible for any misuse or illegal activities involving VulnAPK.
