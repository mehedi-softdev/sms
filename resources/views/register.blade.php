@extends("layout.app")
@section("stylesheet")
    <style>
        .container {
            width: 100vw;
            min-height: 100vh;
            display: flex;
            align-items: center;
            flex-direction: column;
        }
        .login-form {
            
            width: 500px;
            min-height: 300px;
            padding: 5px 1.3em;
            border: 2px solid rgb(196, 187, 187);
            box-shadow: 4px 3px 3px rgb(209, 207, 207);
            display: flex;
            flex-direction: column;
            justify-content: center;
           
        }
        input[type="submit"] {
            float: left;
        }

    </style>
@endsection
@section("container")
    <div class="container">
        <form action="" class="login-form">
            <div class="form-floating mb-3">
                <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
                <label for="floatingInput">Enter a valid email address</label>
              </div>
              <div class="form-floating mb-3">
                <input type="password" class="form-control" id="floatingPassword" placeholder="Password">
                <label for="floatingPassword">Create a Password</label>
              </div>

              <div class="form-floating mb-3 float-end">
                  <input type="submit" value="Signup" class="btn btn-outline-success">
              </div>
              <div class="form-floating mb-3">
                <a href="{{ route("login") }}" class="login">Already have an account ?  </a>
            </div>
        </form>
    </div>
@endsection