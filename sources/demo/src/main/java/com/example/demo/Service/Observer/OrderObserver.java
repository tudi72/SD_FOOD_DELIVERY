package com.example.demo.Service.Observer;

import com.example.demo.Model.MyOrder;

public interface OrderObserver {

    void sendStatus(MyOrder order);
}
