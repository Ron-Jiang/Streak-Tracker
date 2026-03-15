function Login() {
    
    const logIn = () => {
        window.location.href = 'http://localhost:8080/oauth2/authorization/github';
    }

    return (
        <div className="flex justify-center">
            <button onClick={logIn} className="text-green-400 cursor-pointer border-2">Log in with GitHub</button>
        </div>
    )
}

export default Login