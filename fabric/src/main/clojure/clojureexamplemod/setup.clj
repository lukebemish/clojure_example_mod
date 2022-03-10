(ns clojureexamplemod.setup
  (:require [com.github.lukebemish.clojurewrapper.api.event :as event]
            [com.github.lukebemish.clojurewrapper.init :as init])
  (:import (net.fabricmc.fabric.api.event.lifecycle.v1 ServerLifecycleEvents ServerLifecycleEvents$ServerStarted)))

(defn events [] (event/listen ServerLifecycleEvents/SERVER_STARTED
                              ServerLifecycleEvents$ServerStarted
                              (fn [server] (.info init/logger "Server start event fired!"))))