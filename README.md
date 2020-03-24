# ILIAS User Import - IUI
This App generates a XML file from a CSV or Excel file as input. The XML file can then be imported into the [ILIAS e-Learning System](http://www.ilias.de/).

## Download Latest Release
  * [2.0.5](https://github.com/iFadi/ilias-userimport/releases/download/2.0.5/IUI_2.0.5.jar)

## What is it for?
This tool is used to convert a massive number of users from a (CSV, Excel) file to a XML file that can be imported in the [ILIAS e-Learning System](http://www.ilias.de/), it is also able to generate massive test accounts. Originally this tool was developed to simplify the import of users to a separate ILIAS installation which is used only for the purpose of eExams(eKlausuren) and has specific local roles, here is a list of features which this tool can apply to the imported users list:

* Parse Stud.IP Course members CSV/Excel File
* Parse common  CSV/Excel File
* Set a generated or united password to all users
* Set a Global/Local Role
* Set Limited Access. (i.e. only on the day of the exam)
* Generate dummy accounts (i.e. for test purposes)


## Screenshots
![iui_1](https://raw.githubusercontent.com/iFadi/ilias-userimport/master/screenshots/IUI_2.0.2_1.png)
![iui_1](https://raw.githubusercontent.com/iFadi/ilias-userimport/master/screenshots/IUI_2.0.5_2.png)
![iui_1](https://raw.githubusercontent.com/iFadi/ilias-userimport/master/screenshots/IUI_2.0.5_3.png)


## System Requirements
* Windows, Mac, or Linux OS
* [Java 11](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [JavaFX 11](https://openjfx.io/openjfx-docs/#install-javafx)

## Run the Jar file (App)

1. Download and Install [Java 11](https://www.oracle.com/technetwork/java/javase/downloads/index.html) and [JavaFX 11](https://gluonhq.com/products/javafx/) on your OS
2. Based on your OS, set the [PATH_TO_FX](https://openjfx.io/openjfx-docs/#install-javafx)
3. Run the Jar File: `java --module-path $PATH_TO_FX --add-modules=javafx.controls,javafx.fxml -jar IUI_2.0.5.jar`


## Supported ILIAS Versions
* v5.0.x
* v5.1.x
* v5.2.x
* v5.3.x 
* v5.4.x

## Donation

If you find this tool useful or need a fast bug fix you can buy me a Coffee :coffee: to get started :nerd_face:.  
[<img src="https://www.paypalobjects.com/en_US/DK/i/btn/btn_donateCC_LG.gif">](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=J226T349PUB5J&source=url)
 

## Legal
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but **WITHOUT ANY WARRANTY**; without even the implied warranty of
**MERCHANTABILITY** or **FITNESS FOR A PARTICULAR PURPOSE**.  See the
GNU General Public License for more details.
