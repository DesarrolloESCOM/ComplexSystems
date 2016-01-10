#!/usr/bin/env bash
export JAVA_OPTS='-server -Xms2048m -Xmx2048m -XX:PermSize=1024m -XX:MaxPermSize=1024m -XX:+UseParallelOldGC -XX:+UseAdaptiveSizePolicy -XX:+UseBiasedLocking'
export _JAVA_OPTIONS='-Dsun.java2d.opengl=true -Dsun.java2d.xrender=true'
