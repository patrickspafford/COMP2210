import math
from position import Position


class Boggle:
    max_neighbors = 8
    word_list = list()
    board = [['' for x in range(0, 10)] for y in range(0, 10)]
    order = [[0 for g in range(0, 10)] for h in range(0, 10)]
    load_lexicon_called = False
    visited = [[False for c in range(0, 10)] for d in range(0, 10)]
    length = 0
    word_yet = list()
    pathway = [None] * 1000

    def __init__(self):
        self.length = 4
        self.board = [['E', 'E', 'C', 'A'], ['A', 'L', 'E', 'P'], ['H', 'N', 'B', 'O'], ['Q', 'T', 'T', 'Y']]
        for a in range(0, self.length):
            for b in range(0, self.length):
                self.order[a][b] = self.length * a + b

    def load_lexicon(self, file_name):
        if file_name is None:
            raise RuntimeError('Illegal Argument Exception')
        try:
            with open(file_name, 'r') as f:
                for line in f:
                    self.word_list.append(line.split(None, 1)[0].upper())
        except(RuntimeError, EOFError, NameError, FileNotFoundError):
            raise Exception('Illegal Argument Exception: File cannot be opened')
        if len(self.word_list) > 0:
            self.load_lexicon_called = True

    def set_board(self, letter_array):  # ready for testing
        if letter_array is None:
            raise RuntimeError('Illegal Argument Exception')
        mod = len(letter_array) % math.sqrt(len(letter_array))
        if mod != 0:
            raise RuntimeError('Illegal Argument Exception')
        self.length = round(math.sqrt(len(letter_array)))
        for i in range(0, self.length):
            for j in range(0, self.length):
                self.board[j][i] = (letter_array[(self.length * j) + i]).upper()
                self.order[j][i] = self.length * j + i

    def get_all_valid_words(self, min_word_length):
        """
        :type min_word_length: int
        """
        if min_word_length < 1:
            raise RuntimeError('Illegal Argument Exception')
        if not self.load_lexicon_called:
            raise RuntimeError('Illegal State Exception')
        valid_words = list()
        for word in self.word_list:
            if word is not None:
                if len(word) >= min_word_length:
                    position_array = self.is_on_board(word.upper())
                    if position_array is not None:
                        valid_words.append(word.upper())
        return valid_words

    def get_score_for_words(self, words, min_word_length):  # ready for testing
        if min_word_length < 1:
            raise RuntimeError('Illegal Argument Exception')
        if not self.load_lexicon_called:
            raise RuntimeError("Illegal State Exception")
        score = 0
        for word in words:
            if len(word) > min_word_length:
                score += len(word) - min_word_length + 1
        return score

    def is_valid_word(self, word_to_check):  # ready for testing
        if word_to_check is None:
            raise RuntimeError("Illegal Argument Exception")
        if not self.load_lexicon_called:
            raise RuntimeError("Illegal State Exception")
        return word_to_check in self.word_list

    def is_valid_prefix(self, prefix_to_check):
        if prefix_to_check is None:
            raise RuntimeError('Illegal Argument Exception')
        if not self.load_lexicon_called:
            raise RuntimeError('Illegal State Exception')
        for word in self.word_list:
            if prefix_to_check in word:
                return True
        return False

    def is_on_board(self, word_to_check):  # test after dfs
        if word_to_check is None:
            raise RuntimeError('Illegal Argument Exception')
        if not self.load_lexicon_called:
            raise RuntimeError('Illegal State Exception')
        self.clear()
        self.pathway = [None] * 1000
        self.word_yet = list()  # check whether should be global or local
        for k in range(0, self.length):
            for m in range(0, self.length):
                location = Position(k, m, self.board[k][m])
                if self.word_starts_with_same(location.letter.upper(), word_to_check.upper()):
                        if self.dfs_one_word(location.i, location.j,
                                             word_to_check.upper(), self.word_yet, self.pathway):
                            return_pathway = list()
                            for each in self.pathway:
                                if each is not None:
                                    return_pathway.append(each)
                            return return_pathway

    def visit(self, position):  # test ready
        self.visited[position.i][position.j] = True

    def unvisit(self, position):  # test ready
        self.visited[position.i][position.j] = False

    def process(self, position):
        self.word_yet.append(self.board[position.i][position.j].upper())
        self.pathway.append(self.order[position.i][position.j])

    def unprocess(self, position):
        self.word_yet.remove(position.letter)
        self.pathway.remove(self.order[position.i][position.j])

    def is_valid_bounds(self, position):  # ready for testing
        return position.i in range(0, self.length) and position.j in range(0, self.length)

    def is_visited(self, position):  # ready for testing
        return self.visited[position.i][position.j]

    def neighbors(self, position):
        counter: int = 0
        nearby = list()
        for u in range(-1, 2):
            for v in range(-1, 2):
                if not (u == 0 and v == 0) and self.is_in_bounds(position.i + v, position.j + u):
                    neighbor = Position(position.i + v, position.j + u, self.board[position.i + v][position.j + u])
                    nearby.append(neighbor)
                    counter += 1
        return nearby

    def dfs_one_word(self, c, d, search_word, word_so_far, path):
        self.pathway = path
        self.word_yet = word_so_far
        if not self.is_in_bounds(c, d):
            return False
        location = Position(c, d, self.board[c][d])
        if self.is_visited(location):
            return False
        duplicate = self.word_yet.copy()
        duplicate.append(location.letter)
        string_duplicate = ''.join(duplicate)
        if string_duplicate not in search_word:
            return False
        self.visit(location)
        self.process(location)
        if ''.join(self.word_yet) == search_word:
            return True
        else:
            family = self.neighbors(location)
            for neighbor in family:
                if self.dfs_one_word(neighbor.i, neighbor.j, search_word, self.word_yet, self.pathway):
                    return True
            self.unvisit(location)
            self.unprocess(location)
            return False

    @staticmethod
    def word_starts_with_same(string_1, string_2):
        letter_1 = string_1[0].upper()
        letter_2 = string_2[0].upper()
        return letter_1 == letter_2

    def clear(self):
        for a in range(0, self.length):
            for b in range(0, self.length):
                self.visited[b][a] = False
        self.pathway = [None] * 1000
        self.word_yet = list()

    def is_in_bounds(self, i, j):
        return i in range(0, self.length) and j in range(0, self.length)
