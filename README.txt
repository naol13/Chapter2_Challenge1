Part 2: The Critical Analysis

1. Security: Based on the "sandbox" model, list two things you think an applet would NOT be allowed to do on your computer.
   - Applets cannot access or modify files on the local file system outside of the sandbox, preventing unauthorized reading or writing of sensitive data.
   - Applets cannot execute system commands or run external programs on the host machine, limiting potential for malicious actions like launching executables.

2. Modern Replacement: What combination of three modern web technologies (e.g., HTML, CSS, JavaScript) has completely replaced the need for Java Applets? Give a brief example of how you might create a simple bouncing text animation using these technologies.
   - HTML, CSS, and JavaScript have replaced Java Applets.
   - Example: Use HTML to structure the text element (e.g., <div id="bouncing-text">Bounce!</div>), CSS for initial styling and animation (e.g., @keyframes bounce { 0%, 100% { transform: translateY(0); } 50% { transform: translateY(-20px); } } #bouncing-text { animation: bounce 1s infinite; }), and JavaScript to dynamically control the animation if needed (e.g., document.getElementById('bouncing-text').style.animationPlayState = 'paused';).

3. Legacy: Why is it still important for a developer to know what applets are, even if they are deprecated?
   - Understanding applets provides insight into historical web development practices and the evolution of security models, such as the sandbox approach, which influenced modern browser security features.
   - Knowledge of applets helps developers appreciate why certain technologies were replaced and informs decisions on choosing appropriate tools for interactive web content today.
