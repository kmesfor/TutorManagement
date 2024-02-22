package com.gmail.kianmesforush.tutormanagement;

// Logical separation between Popups and Screens where Popups should not call ScreenManager#exit
// or exit the program in any other way. However, they function identically
public interface Popup extends Screen {
}
