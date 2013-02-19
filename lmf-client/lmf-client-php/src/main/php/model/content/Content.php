<?php
/*
 * Copyright (C) 2013 Salzburg Research.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
namespace LMFClient\Model\Content;


/**
 * Created by IntelliJ IDEA.
 * User: sschaffe
 * Date: 25.01.12
 * Time: 14:51
 * To change this template use File | Settings | File Templates.
 */
class Content
{
    /** @var string the buffer containing the data */
    private $data;

    /** @var integer the size of the content */
    private $size;

    /** @var string the mime type of the content */
    private $mimetype;

    function __construct($stream,$size,$mimetype)
    {
        $this->data     = $stream;
        $this->size     = $size;
        $this->mimetype = $mimetype;
    }

    /**
     * @return string
     */
    public function getMimetype()
    {
        return $this->mimetype;
    }

    /**
     * @return int
     */
    public function getSize()
    {
        return $this->size;
    }

    /**
     * @return string
     */
    public function getData()
    {
        return $this->data;
    }


}
