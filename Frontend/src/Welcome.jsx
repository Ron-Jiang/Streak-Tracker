function Welcome({ habits }) {
    return (
        <div className="flex flex-col gap-6 p-9 max-w-lg h-full">
            <p className="text-zinc-500 text0xs tracking-widest mb-1">Dashboard</p>
            <h1 className="text-white font-bold tracking-wider text-4xl">Track Your Habits, It'll be Worth It</h1>
            <div className="flex flex-col gap-2 overflow-y-auto">
                {habits?.length === 0 && (<p className="text-zinc-500 text-sm">No habits tracked yet... why not create one?</p>)}
                {habits?.map(habit => (
                    <div key={habit.id} className="bg-zinc-900 border border-zinc-700 rounded-lg px-4 py-3 flex items-center justify-between">
                        <p className="text-zinc-200 text-sm">{habit.habitName}</p>
                        <p className="text-amber-500 font-bold text-base tracking-wide">{habit.currentStreak} DAY{habit.currentStreak !== 1 ? 'S' : ''}</p>
                    </div>
                    
                ))}
            </div>
        </div>
    )
}

export default Welcome