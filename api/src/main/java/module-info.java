module textify.api {
  // REST DEPENDENCIES
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.beans;
  requires spring.web;
  requires spring.data.rest.core;

  // JPA DEPENDENCIES
  requires java.persistence;
  requires spring.data.commons;
  requires spring.context;
}