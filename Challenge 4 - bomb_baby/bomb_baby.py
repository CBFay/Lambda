# Created on 11.12.2017 by CB Fay

def answer(M, F):
	return str((fewest_generations(M, F, 1)))

# O(a%b)	
def fewest_generations(a, b, gens):
	if a > b:
		big = int(a)
		small = int(b)
	else:
		big = int(b)
		small = int(a)
	
	# impossible replication paths
	if a == b:
		return "impossible"
	if big % 2 == 0 and small % 2 == 0:
		return "impossible"
	if big == 0 or small == 0:
		return "impossible"
		
	# base case
	if small == 1:
		return gens+big-2
		
	return fewest_generations(small, big%small, gens+big//small)