(ns clojureexamplemod.setup
  (:require [com.github.lukebemish.clojurewrapper.api.event :as event]
            [com.github.lukebemish.clojurewrapper.init :as init])
  (:import (net.minecraftforge.fml.event.lifecycle FMLCommonSetupEvent)))

(defn events [] (event/listen FMLCommonSetupEvent
                              (fn [event] (.info init/logger "Common setup event fired!"))))