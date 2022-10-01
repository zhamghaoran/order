package com.order.Service;

import com.order.Dao.pojo.Root;

public interface RootService {
    Root FindRootByUID(String uid);

    Root Login(String username, String password);

}
