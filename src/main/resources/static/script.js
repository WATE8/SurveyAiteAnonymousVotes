document.getElementById('poll-form').addEventListener('submit', async function(e) {
  e.preventDefault();
  const formData = new FormData(e.target);
  const vote = formData.get('vote');
  if (!vote) return alert("Выберите вариант");

  await fetch('/vote', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ vote })
  });

  loadResults();
});

async function loadResults() {
  const res = await fetch('/results');
  const data = await res.json();
  const results = document.getElementById('results');
  results.innerHTML = '';
  for (const [option, count] of Object.entries(data)) {
    const li = document.createElement('li');
    li.textContent = `${option}: ${count} голосов`;
    results.appendChild(li);
  }
}

loadResults();
