# java-graceful-shutdown-sample
Sample application to demonstrate the threads to be shutdown gracefully

## How to run this project
`mvn clean package` <br/>
`cd target` <br/>
`java -jar <jarname>` (to run as background process, check below items)

## Observations
1. While stopping process from IDE console, it won't executing shutdown hook (tested with Eclipse)
2. While stopping process using `TASKKILL /F` command from Windows, it won't executing shutdown hook (it's not stopping without using `/F` option)
3. While stopping process using `Ctl + C` from Windows, it will execute the shutdown hook
4. While stopping process using `kill <PID>` from Linux, it will execute the shutdown hook (not the `kill -9`)

## Useful commands for this project

### Run java process on Windows & Linux
`java -jar test.jar`

### Run java process in background on Windows
`javaw -jar test.jar`

### Run java process in background on Linux
`nohup java -jar test.jar &`

### Finding java process id using command line on Windows
`C:` <br/>
`cd "C:\Program Files\Java\jdk1.8.0_162\bin"` <br/>
`jps -lv`

### Kill process on windows
`TASKKILL /F /<PID>` (forcefully) <br/>
`TASKKILL /<PID>` (gracefully, this way is not working to kill java background process)

### Finding java process ids using command on Linux
`ps aux | grep java`

### Kill process on linux 
`kill -9 <PID>` (forcefully) <br/>
`kill <PID>` (gracefully)
