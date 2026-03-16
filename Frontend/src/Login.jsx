function Login() {
    
    const logIn = () => {
        window.location.href = 'http://localhost:8080/oauth2/authorization/github';
    }

    return (
        <div className="flex flex-col items-center justify-center h-screen bg-zinc-950 gap-4">
            <h1 style={{animation: 'typewriter 3.5s steps(30) forwards', overflow: 'hidden', whiteSpace: 'nowrap', width: '0', maxWidth: 'fit-content'}} className="text-amber-500 font-bold text-5xl">STREAKS TRACKER</h1>
            <p style={{animation: 'typewriter 3.5s steps(40) forwards', animationDelay: '1s', overflow: 'hidden', whiteSpace: 'nowrap', borderRight: '2px solid #f59e0b', width: '0', maxWidth: 'fit-content', animationFillMode: 'forwards'}} className="text-zinc-500 text-xs tracking-wider">Maintain your streak. Build good habits. Let us help.</p>
            <button onClick={logIn} className="mt-4 px-6 py-3 rounded-lg bg-zinc-900 border border-zinc-700 text-zinc-200 text-xs tracking-wider hover:border-amber-500 hover:text-amber-500 transition-colors cursor-pointer">Log in with GitHub</button>
        </div>
    )
}

export default Login