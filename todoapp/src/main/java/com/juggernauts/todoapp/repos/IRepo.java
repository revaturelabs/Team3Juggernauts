package com.juggernauts.todoapp.repos;

import java.util.List;

    public abstract class IRepo<T> {

        public abstract int register(T user);



        public abstract int login(String username, String password);


        public abstract String viewProfile(String username);


        public abstract String updateProfile( String username, String password, String email);


        public abstract T get(String username);


        public abstract List<T> getAll();
    }

}
