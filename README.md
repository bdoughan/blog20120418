Extending JAXB - Representing Metadata as JSON
==============================================

This is the complete source code for the following blog post:

* http://blog.bdoughan.com/2012/04/extending-jaxb-representing-metadata-as.html

Summary
-------

In EclipseLink 2.4 the MOXY component includes support for representing the mapping metadata as JSON.  Now you can have a binding file like:

    {
       "package-name" : "blog.bindingfile",
       "xml-schema" : {
          "element-form-default" : "QUALIFIED",
          "namespace" : "http://www.example.com/customer"
       },
       "java-types" : {
          "java-type" : [ {
             "name" : "Customer",
             "xml-type" : {
                "prop-order" : "firstName lastName address phoneNumbers"
             },
             "xml-root-element" : {},
             "java-attributes" : {
                "xml-element" : [ 
                    {"java-attribute" : "firstName","name" : "first-name"}, 
                    {"java-attribute" : "lastName", "name" : "last-name"}, 
                    {"java-attribute" : "phoneNumbers","name" : "phone-number"}
                ]
             }
          }, {
             "name" : "PhoneNumber",
             "java-attributes" : {
                "xml-attribute" : [ 
                    {"java-attribute" : "type"}
                ],
                "xml-value" : [ 
                    {"java-attribute" : "number"}
                ]
             }
          } ]
       }
    }
    
Compile the Example
-------------------

You can compile the example code using the following command.  Maven will automatically fetch the required dependencies.

     mvn compile

Run the Example
---------------

You can run the example using the following command.

    mvn exec:java