# Bible Lib

*Note* This library is a work in progress, so use it as a stepping-stone for your own application or help move it along by making a PR to help complete it.

This is currently not production ready, but the intention is to get it to a point where it can be. Feel free 

## What is this?
A library for interacting with and parsing the data within the bible.

This library is meant for comparing, parsing, and searching data within a dataset as well as consuming APIs and ensuring the data is formatted to be used as intended within other applications.

*Note*
This utilizes the Luke Smith edited version of the KJV bible for testing purposes. In the future I plan for it to consume API data and built data structured in a similar way for offline consumption.

## Usage
Using your Clojure REPL you can test out some of the functions and see the results;

Using this library in your project
```clojure
(ns bible-lib.core 
  (:require [bible-lib :as bible]))

(def bible-location "~/Downloads/bible_nkj.txt")

(first (bible/data->list bible-location))

(write-file (str/join " " (bible/data-books-list (bible/data-bible-loc)))
            "books_list.txt")
```

```clojure
;Example for returning the first verse in Genesis
(first (bible/data->list bible/data-bible-loc)) ;=> ["Genesis" "Ge" "1" "1" "1" "In the beginning God created the heaven and the earth."]
```

Here's another example for getting the frequency of words used in the bible
```clojure
(frequency-data (data->list data-bible-loc))
```
[View result](https://gist.github.com/MaterialFuture/906317ac3c9e41ad7da76457827175c3)

If you want to write results to a file you can do so like below
```clojure
 (frequency-data (data->list data-bible-loc))
```

## Future Plans

Future plans will be to take API data or other bible datasets and format them into hash maps;
```clojure
{:book "1 John" :verse "3:16" :content "For God so loved the world that He gave His only begotten Son, that whoever believes in Him should not perish but have everlasting life." ...}
```

## License
TODO: Change license

Copyright © 2022 @MaterialFuture

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
