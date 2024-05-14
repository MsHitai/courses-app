fetch('http://localhost:8080/courses')
  .then(response => response.json())
  .then(data => {
    const courses = data;
    let courseListHTML = '';
    for (const course of courses) {
      courseListHTML += `
                <div class="col-md-3 mb-4">
                    <div class="card h-100 shadow-sm">
                        ${course.title}
                        <p><strong>Author:</strong> ${course.author}</p>
                        <p><small>Lessions: ${course.lessons.length}</small></p>
                    </div>
                </div>
            `;
    }
    document.getElementById('courses').innerHTML = courseListHTML;
  })
  .catch(error => console.error(error));
