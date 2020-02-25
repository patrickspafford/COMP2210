from Boggle import Boggle


def main():
        file = "words_medium.txt"
        game = Boggle()
        game.load_lexicon(file)
        board = ['P', 'E', 'T', 'R']
        game.set_board(board)
        print('pet is on the board at the following positions:')
        print(game.is_on_board('pet'))
        print('Pope is on the board at the following positions:')
        print(game.is_on_board('POPE'))
        print('Here are all the valid words of length 1 or greater:')
        print(game.get_all_valid_words(1))


if __name__ == '__main__':
            main()
