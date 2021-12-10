<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class LoginController extends Controller
{
    //login
    public function login(Request $request)
    {
        return view("login")->with([
            
            'title' => "Login",

        ]);

    }

        //register
    public function register(Request $request)
       {
           return view("register")->with([
                
                'title' => "Register",
    
            ]);
       }
}
