# FormValidator

Android library designed for rapid and customizable form validation.

[![Release](https://jitpack.io/v/pchmn/FormValidator.svg)](https://jitpack.io/#pchmn/FormValidator)

## Setup

To use this library your `minSdkVersion` must be >= 15.

In your project level build.gradle :
```java
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}       
```

In your app level build.gradle :
```java
dependencies {
    compile 'com.github.pchmn:RxSocialAuth:2.0.1-beta'
}      
```

## Usage

You can use **FormValidator** with any `View` that extends the original [`EditText`](https://developer.android.com/reference/android/widget/EditText.html) (such as 
[`MaterialEditText`](https://github.com/rengwuxian/MaterialEditText) for example).

### With XML

You just have to wrap your `EditText` with an `InputValidator` view. Example for an email and a custom regex :

```xml
<com.pchmn.formvalidator.InputValidator
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:required="true"
    app:requiredMessage="Email required">

    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:inputType="textEmailAddress"
        android:hint="Email"/>

</com.pchmn.formvalidator.InputValidator>
            
<com.pchmn.formvalidator.InputValidator
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:regex="^[0-9]{4}$"
    app:errorMessage="4 digits only">

    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Regex 4 digits (custom error msg)"/>

</com.pchmn.formvalidator.InputValidator>            
```
`InputValidator` will automatically recognized **textEmailAdress**, **phone** and **number** `inputType` and use the appropriate validator, like in the example with the email field.
<br>If you don't specify an `errorMessage` or a `requiredMessage`, predefined messages will be shown if the field is not valid.

Then **validate** your form :
```java
// initiate from an activity
// 'this' represents an Activity
// you can specify if you want to show error messages or not
Form form = new Form.Builder(this)
    .showErrors(true)
    .build();

// or initiate from a fragment or from what you want by providing your own root view
Form form = new Form.Builder(getContext(), rootView)
    .showErrors(true)
    .build();

// validate the form
if(form.isValid()) {
    // the form is valid
}
else {
    // the form is not valid
}        
```

### With Java

You can create programmatically `InputValidator` without passing by XML ([see all Builder methods](#inputvalidator-builder)) :

```java
// create the validator with the Builder
// emailEditText is the EditText to validate 
// 'this' represents a Context
InputValidator emailValidator = new InputValidator.Builder(this)
    .on(emailEditText)
    .required(true)
    .validatorType(InputValidator.IS_EMAIL)
    .build();
                
// create the form and add the validator
Form form = new Form.Builder(this)
    .addInputValidator(emailValidator)
    .build();
    
// validate the form
if(form.isValid()) {
    // the form is valid
}
else {
    // the form is not valid
}
```


### Attributes

#### `InputValidator`
All the attributes that can be used with the `InputValidator` view. They can be used in XML or in Java with setters :

Attribute | Type | Description
--- | --- | ---
**required** | `boolean` | Whether the field is required or not
**validator** | `enum` | Use a validator type predefined by FormValidator. You can use **isEmail**, **isPhoneNumber**, **isNumeric**, **isUrl** or **isIP**
**minLength** | `int` | The minimum length of the field
**maxLength** | `int` | The maximum length of the field
**minValue** | `int` | The minimum value of the field (must be numeric)
**maxValue** | `int` | The maximum value of the field (must be numeric)
**regex** | `string` | Use a regex to validate a field
**identicalAs** | `reference id` | The id of an EditText to which the field must be equal
**errorMessage** | `string` | The message to display if the field is not valid
**requiredMessage** | `string` | The message to display if the field is empty but was required

#### `Form`
All the attributes that can be used with the `Form` view. They can be used in XML or in Java with setters :

Attribute | Type | Default | Description
--- | --- | --- | ---
**showErrors** | `boolean` | `true` | Whether the errors must be shown on each EditText or not


## Advanced Usage

### Use a custom validator

You can use a custom validator for an `InputValidator` : 
```java
// the InputValidator was present in the XML layout
InputValidator inputValidator = (InputValidator) findViewById(R.id.input_validator);
// your custom validator must extends AbstractValidator class
inputValidator.setCustomValidator(new AbstractValidator() {
    @Override
    public boolean isValid(String value) {
        return value.equals("ok man");
    }
              
    @Override
    public String getErrorMessage() {
        return "This field must be equals to 'ok man'";
    }
});


// or create your InputValidator with the Builder
InputValidator inputValidator = new InputValidator.Builder(this)
    .on(anEditText)
    .customValidator(new AbstractValidator() {
        @Override
        public boolean isValid(String value) {
            return value.equals("ok man");
        }
                  
        @Override
        public String getErrorMessage() {
            return "This field must be equals to 'ok man'";
        }
    });
    .build();
```


### Use the `Form` view in XML

If you want, you can use a `Form` view directly in XML. This view extends [`LinearLayout`](https://developer.android.com/reference/android/widget/LinearLayout.html). It must wrap all the fields you want to check.

It can be useful for these reasons : 
* You don't have to instantiate a `Form` object before validate the form
* It will be easier to identify a form in your XML layout
* You can use two different and independent forms in the same XML layout

#### XML
```xml
    <!-- form1 -->
    <com.pchmn.formvalidator.Form
        android:id="@+id/form1"
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:showErrors="false">

        <!-- email -->
        <com.pchmn.formvalidator.InputValidator
            android:layout_width="wrap_content"
            android:layout_height="wrap_content">

            <EditText
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:inputType="textEmailAddress"
                android:hint="Email"/>

        </com.pchmn.formvalidator.InputValidator>

        <!-- password -->
        <com.pchmn.formvalidator.InputValidator
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:required="true"
            app:minLength="6">

            <EditText
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:inputType="textPassword"
                android:hint="Password (6 char. min) *" />

        </com.pchmn.formvalidator.InputValidator>

    </com.pchmn.formvalidator.Form>
    <!-- /form1 -->

    <!-- form2 -->
    <com.pchmn.formvalidator.Form
        android:id="@+id/form2"
        android:orientation="vertical"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <!-- phone number -->
        <com.pchmn.formvalidator.InputValidator
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:required="true">

            <EditText
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:inputType="phone"
                android:hint="Phone number *"/>

        </com.pchmn.formvalidator.InputValidator>

        <!-- password -->
        <com.pchmn.formvalidator.InputValidator
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:minValue="12">

            <EditText
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:inputType="number"
                android:hint="Age (12 min)" />

        </com.pchmn.formvalidator.InputValidator>

    </com.pchmn.formvalidator.Form>
    <!-- /form2 -->
```

#### Validate the forms

```java
// get the forms
// you don't have to instantiate them because they already know the fields they have to validate
Form form1 = (Form) findViewById(R.id.form1);
Form form2 = (Form) findViewById(R.id.form2);

// validate form1
if(form1.isValid()) {
    // form1 is valid
}

// validate form2
if(form2.isValid()) {
    // form2 is valid
}

```


### Builders method

It is recommended to use the builders to create `Form` and `InputValidator` views programmatically in order to prevent some errors. All the attributes for the two views are supported by the builders.
 
#### `InputValidator` builder

```java
InputValidator inputValidator = new InputValidator.Builder(this)
    // methods
    .build();
```


Method | Return value | Description
--- | --- | ---
**InputValidator.Builder**(`Context` context) | `InputValidator.Builder` | The constructor of the builder
**on**(`EditText` editText) | `InputValidator.Builder` | The EditText to validate
**required**(`boolean` required) | `InputValidator.Builder` | Whether the field is required or not
**validatorType**(`int` type) | `InputValidator.Builder` | Use a validator type predefined by FormValidator. You can use **InputValidator.IS_EMAIL**, **InputValidator.IS_PHONE_NUMBER**, **InputValidator.IS_NUMERIC**, **InputValidator.IS_URL** or **InputValidator.IS_IP**
**customValidator**(`AbstractValidator` validator) | `InputValidator.Builder` | Use a custom validator
**minLength**(`int` length) | `InputValidator.Builder` | The minimum length of the field
**maxLength**(`int` length) | `InputValidator.Builder` | The maximum length of the field
**minValue**(`int` value) | `InputValidator.Builder` | The minimum value of the field (must be numeric)
**maxValue**(`int` value) | `InputValidator.Builder` | The maximum value of the field (must be numeric)
**regex**(`String` regex) | `InputValidator.Builder` | Use a regex to validate a field
**identicalAs**(`int` id) | `InputValidator.Builder` | The id of an EditText to which the field must be equal
**errorMessage**(`String` message) | `InputValidator.Builder` | The message to display if the field is not valid
**requiredMessage**(`String` message) | `InputValidator.Builder` | The message to display if the field is empty but was required
**build()** | `InputValidator` | Create the `InputValidator` object

#### `Form` builder

```java
Form form = new Form.Builder(this)
    // methods
    .build();
```

Method | Return value | Description
--- | --- | ---
**Form.Builder**(`Activity` activity) | `Form.Builder` | First constructor of the builder
**Form.Builder**(`Context` context, `View` rootView) | `Form.Builder` | Second constructor of the builder
**addInputValidator**(`InputValidator` validator) | `Form.Builder` | Add an `InputValidator`
**showErrors**(`boolean` show) | `Form.Builder` | Whether the errors must be shown on each EditText or not
**build()** | `Form` | Create the `Form` object

## Sample

A sample app with some use cases of the library is available on this [link](https://github.com/pchmn/FormValidator/tree/master/sample)

## Credits

* [Ratifier](https://github.com/hamadakram/ratifier?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=5441)
* [Android-Validator](https://github.com/throrin19/Android-Validator)

## License

```
Copyright 2017 pchmn

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
