<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".SecondActivity">

    <ListView
        android:id="@+id/lvlista"
        android:layout_width="190dp"
        android:layout_height="315dp"
        android:layout_marginStart="16dp"
        android:layout_marginTop="16dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/button"
        android:layout_width="126dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="144dp"
        android:layout_marginTop="32dp"
        android:text="@string/bAnterior"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/tvInfo" />

    <RadioGroup
        android:layout_width="113dp"
        android:layout_height="226dp"
        android:layout_marginStart="48dp"
        android:layout_marginTop="60dp"
        app:layout_constraintStart_toEndOf="@+id/lvlista"
        app:layout_constraintTop_toTopOf="parent" >

        <RadioButton
            android:id="@+id/radioButton2"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:checked="true"
            android:text="@string/enero" />

        <RadioButton
            android:id="@+id/radioButton3"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="@string/fegrero" />

        <RadioButton
            android:id="@+id/radioButton4"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="@string/marzo" />

        <RadioButton
            android:id="@+id/radioButton"
            android:layout_width="ma