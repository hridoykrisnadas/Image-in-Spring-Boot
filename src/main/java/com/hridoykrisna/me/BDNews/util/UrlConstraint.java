package com.hridoykrisna.me.BDNews.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UrlConstraint {
    public static final String API = "/api";
    public static final String VERSION = "/v1";

    public static class AuthManagement {
        public static final String ROOT = API+"/auth";
        public static final String REGISTER = "/register";
        public static final String LOGIN = "/login";
    }

    public static class CategoryManagement {
        public static final String ROOT = API+VERSION+"/category";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update";
        public static final String DETAILS = "/details/{id}";
        public static final String DELETE = "/delete/{id}";
        public static final String ALL_CATEGORY = "/all-category";
    }
    public static class PostManagement {
        public static final String ROOT = API+VERSION+"/post";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update";
        public static final String DETAILS = "/details/{id}";
        public static final String DELETE = "/delete/{id}";
        public static final String ALL_POST = "/all-post";
    }
}
