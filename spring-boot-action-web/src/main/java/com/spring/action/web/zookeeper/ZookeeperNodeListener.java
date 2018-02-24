package com.spring.action.web.zookeeper;

import java.util.Set;

/**
 * Created by fgm on 2017/5/25.
 */
public interface ZookeeperNodeListener {

    void  notifyNodeChange(Set<String> serverInfo);

}
