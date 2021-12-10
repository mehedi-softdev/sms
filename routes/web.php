<?php

use App\Http\Controllers\LoginController;
use App\Http\Controllers\MainController;
use Illuminate\Support\Facades\Route;


Route::get("/", [MainController::class, 'home'])->name("home");

// login routes
Route::any("/login", [LoginController::class, 'login'])->name("login");
Route::any("/register", [LoginController::class, 'register'])->name("register");
