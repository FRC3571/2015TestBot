# 2015TestBot

This is created with Eclipse for the purpose to be a tool for learting how to program the the 2015 FRC robot

<ul><b>Requirements:</b>
<li>Eclipse 4.4 or later (Currently Codename Luna or Mars)</li>
<li>Eclipse IDE for C/C++ Developers</li>
<li>Java Development Tools</li>
<li>Robot Java Development</li>
</ul>
<ol><b>How to get the Requirements:</b>
<li>Go to <b>http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html</b></li>
<li>Download the correct jdk for your system</li>
<li>Once installed go to <b>https://www.eclipse.org/downloads/</b></li>
<li>Download <b>Eclipse IDE for C/C++ Developers</b></li>
<li>Unzip the downloaded file</li>
<li>(This program does not install on your machine,<br/> please place the entire unzipped folder where it is least likely to be deleted)</li>
<li>Run the file <b>Eclipse.exe</b></li>
<li>Go to <b>Help → Install New Software</b></li>
<li>In the dropdown menu next to <b>"Work with"</b> sellect the codename of the Eclipse you are using<br/>
Currently it is <b>Mars</b></li>
<li>Under <b>Programming Languages</b> select <b>Eclipse Java Development Tools</b></li>
<li>Click on <b>Next</b> → <b>Next</b> → <b>Accept</b> → <b>Finish</b></li>
<li>Once Eclipse Restarts go back to <b>Install New Software</b></li>
<li>Click on <b>add</b> and enter:<br/>
    Name: <b>FRC</b><br/>
    Location: <b>http://first.wpi.edu/FRC/roborio/release/eclipse/</b></li>
<li>Under <b>WPILib Robot Development</b> sellect <b>Robot Java Development</b></li>
<li>Click on <b>Next</b> → <b>Next</b> → <b>Accept</b> → <b>Finish</b> and allow it to install<br/>
If you get a security warning, just click OK</li>
<li>Go to <b>Window</b> → <b>Preferences</b></li>
<li>In preferences expand <b>Java</b> and click on <b>Installed JREs</b></li>
<li>Click <b>Add</b> → <b>Standard VM</b> → <b>Next</b></li>
<li>Click <b>Directory...</b> and find the directory of the JDK you just installed<br/>
    <b>Ex:</b> In Windows I would go to <b>C:\Program Files\Java</b> and sellect <b>jdk1.8.0_73</b> <br/>
    Warning sellect your <b>JDK</b> folder not the JRE</li>
<li>Then <b>select</b> the <b>jdk</b> you just added and click OK</li>
<li>Finaly to generate the FRC Java library, create a <b>new frc project</b><br/>
<ol><li>Go to <b>File</b> → <b>New</b> → <b>Project</b></li>
	<li>Go to <b>WPILib Robot Java Development</b> and click on <b>Robot Java Project</b></li>
	<li>Enter in a random project name and click <b>Finish</b><br/>The name just has to not be empty</li></ol></li>
</ol>

<ol>To Import this project:
<li>Open Eclipse</li>
<li>Go to <b>File</b> → <b>Import</b> → <b>Git</b> → <b>Projects from Git</b> → <b>Clone URl</b></li>
<li>Enter the Following<br/>
URl: <b>https://github.com/FRC3571/2015TestBot.git</b><br/>
(Do not touch the other fields)</li>
<li>After you click next and finish the project will be automatically downloaded</li>
</ol>
<b>If you have a problem with any of this than create an issue under the issue tab and I will get back to you as soon as I can</b>
