%Modules
module Controller:    myapp.controller.*
module View:          myapp.view.*
module Model:         myapp.model.**
module Domain:        myapp.model.domain.*
module Util:          myapp.util.*
module DAO:           "myapp.model.[a-zA-Z0-9/.]*DAO"
module JavaAwtSwing:  java.awt.**, javax.swing.**
module JavaSql:       java.sql.**
 
%Constraints
only View can-depend JavaAwtSwing
only DAO can-depend JavaSql
View cannot-depend Model
Domain can-depend-only $java
DAO can-depend-only Domain, Util, javaSql
Util cannot-depend $system