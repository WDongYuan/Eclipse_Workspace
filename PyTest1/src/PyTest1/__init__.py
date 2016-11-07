class ValidWordAbbr(object):
    def __init__(self, dictionary):
        """
        initialize your data structure here.
        :type dictionary: List[str]
        """
        self.dic = {}
        self.set = set()
        for s in dictionary:
            self.set.add(s)
        ss = ""
        for s in self.set:
            if s == "":
                ss = "0"
            else:
                ss = s[0]+str((len(s)-2))+s[len(s)-1]
            if ss in self.dic:
                self.dic[ss] = self.dic[ss]+1
            else:
                self.dic[ss] = 1
        

    def isUnique(self, word):
        """
        check if a word is unique.
        :type word: str
        :rtype: bool
        """
        ss = ""
        if word == "":
            ss = "0"
        else:
            ss = word[0]+str((len(word)-2))+word[len(word)-1]
        print(ss)
        if (word not in self.set and ss in self.dic) or (word in self.set and self.dic[ss]!=1):
            return False
        return True
dic = [ "deer", "door", "cake", "card" ,"","a","a"]
shit = ValidWordAbbr(dic)
print(shit.isUnique("dear"))
print(shit.isUnique("cart"))
print(shit.isUnique("cane"))
print(shit.isUnique("make"))
print(shit.isUnique(""))
print(shit.isUnique("a"))