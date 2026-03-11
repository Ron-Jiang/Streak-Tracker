function Welcome({habits}) {
    return (
        <>
            <div className="flex flex-col">
                <h1 className="text-amber-50">WELCOME BACK! </h1>
                <div className="flex flex-col">
                    {habits?.map(habit => (
                        <p key={habit.id} className="text-amber-300">{habit.habitName} and streak: {habit.currentStreak}</p>
                    ))}
                </div>
            </div>
        </>
    )
}

export default Welcome