function SideBar({habits, onSelect}) {
    return (
        <>
            <div className="bg-black fixed h-screen w-20 flex flex-col">
                <button onClick={() => onSelect('home')} className="w-16 h-16 mt-10 rounded-full bg-yellow-300 mx-auto cursor-pointer"/>

                {habits?.map(habit => (
                    <button key={habit.id} onClick={() => onSelect(habit.id)} className="w-16 h-16 mt-10 rounded-full bg-red-600 mx-auto cursor-pointer"/>
                ))}

                <button onClick={() => onSelect('add')} className="w-16 h-16 mt-10 rounded-full bg-yellow-300 mx-auto cursor-pointer"/>
            </div>
        </>
    )
};


export default SideBar