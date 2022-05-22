from panflute import *
from sys import stderr

headers = {}


def duplicateHeaders(elem, doc):
    if type(elem) == Header:
        if stringify(elem) in headers.keys():
            if headers.get(stringify(elem)) == elem.level:
                print("Warning: duplicate header on level " + str(elem.level) + ". Content: " + stringify(elem), file=stderr)
        else:
            headers[stringify(elem)] = elem.level


def bold(doc):
    doc.replace_keyword("BOLD", Strong(Str("BOLD")))


def upperHeader(elem, _):
    if isinstance(elem, Header) and elem.level >= 3:
        return Header(Str(stringify(elem).upper()), level=elem.level)


if __name__ == "__main__":
    run_filters([duplicateHeaders, upperHeader], prepare=bold)
