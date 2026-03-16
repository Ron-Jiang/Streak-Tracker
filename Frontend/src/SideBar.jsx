function SideBar({ habits, onSelect }) {
    return (
        <div className="fixed left-0 bottom-0 top-14 w-20 bg-zinc-950 border-r border-zinc-800 flex flex-col pb-4 pt-4">
            <button onClick={() => onSelect('home')} className="w-12 h-12 rounded-full mx-auto shrink-0 bg-zinc-900 border border-zinc-700 flex items-center justify-center text-sm text-zinc-500 hover:border-amber-500 transition-colors cursor-pointer">Home</ button>
            {/* divider */}
            <div className="w-10 h-px bg-zinc-700 mx-auto my-3" />
            {/* habit circles */}
            <div className="flex-1 overflow-y-auto flex flex-col items-center gap-5 py-4">
                {habits?.map(habit => (
                    <button key={habit.id} onClick={() => onSelect(habit.id)} className="w-12 h-12 rounded-full mx-auto shrink-0 bg-zinc-900 border border-zinc-700 flex items-center justify-center text-amber-500 font-bold text-lg hover:border-amber-500 transition-colors cursor-pointer">{habit.habitName?.[0]?.toUpperCase()}</button>
                ))}
            </div>
            {/* divider */}
            <div className="w-10 h-px bg-zinc-700 mx-auto my-3" />
            {/* add habit */}
            <button onClick={() => onSelect('add')} className="w-12 h-12 border border-dashed border-zinc-600 rounded-full mx-auto shrink-0 flex items-center justify-center text-sm text-zinc-500 hover:border-amber-500 transition-colors cursor-pointer">Add</button>
        </div>
    )
};


export default SideBar