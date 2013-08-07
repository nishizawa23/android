Following is the setp to use eclim build android project

1.execute eclimd

2.ProjectCreat ./ -n android

3.Ant(to build project)
	Notes: if there is no build.xml, execute 
	:!android update project -p .

4.To start an AVD,use
	android list avd
	emulator -avd <avd_name>

5.To upload project to default emulator or device
	:Ant debug/release install

6.To turn off eclimd, in vim, type :ShutdownEclim,
	or in command line, eclim -command shutdown
