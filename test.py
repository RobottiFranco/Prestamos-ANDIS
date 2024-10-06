import requests
import concurrent.futures

url = 'http://localhost:8080/prestamos/ObtenerNoPagados'

def make_request():
    try:
        response = requests.get(url)
        print(f'Response: {response.status_code}, {response.text}')
    except requests.exceptions.RequestException as e:
        print(f'Error: {e}')

# solicitudes concurrentes
num_requests = 3

# enviar solicitudes simultaneamente
with concurrent.futures.ThreadPoolExecutor(max_workers=num_requests) as executor:
    futures = [executor.submit(make_request) for _ in range(num_requests)]

concurrent.futures.wait(futures)